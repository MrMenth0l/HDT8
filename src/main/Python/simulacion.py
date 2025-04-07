
import simpy
import random
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# Parámetros de la simulación
RANDOM_SEED = 42
SIM_TIME = 1000        # Tiempo total de simulación
ARRIVAL_INTERVAL = 10  # Intervalo medio entre llegadas

# Tiempos de servicio (unidades de tiempo)
TRIAGE_TIME = 10
DOCTOR_TIME = {1: 15, 2: 20, 3: 25, 4: 30, 5: 35}
XRAY_TIME = 20
LAB_TIME = 15

# Estructura para recolectar métricas de espera
wait_times = {
    'triage': [],
    'doctor': [],
    'xray': [],
    'lab': [],
    'total': []
}

def patient_arrivals(env, triage, doctors, xray, lab):
    """Generador de llegadas de pacientes."""
    i = 0
    while True:
        yield env.timeout(random.expovariate(1.0 / ARRIVAL_INTERVAL))
        i += 1
        env.process(handle_patient(env, f'Paciente-{i}', triage, doctors, xray, lab))

def handle_patient(env, name, triage, doctors, xray, lab):
    """Proceso que modela el flujo de un paciente en la sala de emergencias."""
    arrival_time = env.now

    # 1) Triage (sin prioridad)
    with triage.request() as req:
        yield req
        yield env.timeout(TRIAGE_TIME)
        wait_times['triage'].append(env.now - arrival_time)

    # Asignar severidad aleatoria entre 1 (más urgente) y 5 (menos urgente)
    severity = random.randint(1, 5)

    # 2) Consulta médica (PriorityResource)
    with doctors.request(priority=severity) as req:
        yield req
        start = env.now
        yield env.timeout(DOCTOR_TIME[severity])
        wait_times['doctor'].append(start - arrival_time)

    # 3) Rayos X
    with xray.request(priority=severity) as req:
        yield req
        start = env.now
        yield env.timeout(XRAY_TIME)
        wait_times['xray'].append(start - arrival_time)

    # 4) Laboratorio
    with lab.request(priority=severity) as req:
        yield req
        start = env.now
        yield env.timeout(LAB_TIME)
        wait_times['lab'].append(start - arrival_time)

    # 5) Total en sistema
    wait_times['total'].append(env.now - arrival_time)

def run_simulation(num_nurses=2, num_doctors=2, num_xrays=1, num_labs=1):
    """Configura y ejecuta la simulación con los recursos dados."""
    random.seed(RANDOM_SEED)
    env = simpy.Environment()

    # Recursos
    triage = simpy.Resource(env, capacity=num_nurses)
    doctors = simpy.PriorityResource(env, capacity=num_doctors)
    xray = simpy.PriorityResource(env, capacity=num_xrays)
    lab = simpy.PriorityResource(env, capacity=num_labs)

    # Iniciar llegadas
    env.process(patient_arrivals(env, triage, doctors, xray, lab))

    # Ejecutar simulación
    env.run(until=SIM_TIME)

    # Analizar resultados
    df = pd.DataFrame({k: pd.Series(v) for k, v in wait_times.items()})
    print(df.describe())

    # Gráfica del tiempo total en sala
    sns.histplot(df['total'], kde=True)
    plt.title('Distribución de tiempo total en sala de emergencias')
    plt.xlabel('Tiempo')
    plt.ylabel('Frecuencia')
    plt.show()

if __name__ == '__main__':
    run_simulation()

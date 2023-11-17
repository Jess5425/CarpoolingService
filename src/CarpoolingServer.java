import java.util.*;

/**
 * Clase que representa un servidor de carpools con funcionalidades como cálculo de rutas, registro de empleados y conductores, y estadísticas de conductores.
 */
public class CarpoolingServer {
    /**
     * Grafo que representa las conexiones y tiempos de viaje entre destinos.
     */
    private Map<String, Map<String, Integer>> graph; // Grafo que representa las conexiones y tiempos de viaje entre destinos

    /**
     * Ubicación de la empresa en el grafo.
     */
    private String companyLocation;

    /**
     * Constructor que inicializa el servidor de carpooling y crea el grafo con destinos y tiempos de viaje aleatorios.
     */
    public CarpoolingServer() {
        this.graph = new HashMap<>();
        initializeMap();
    }

    /**
     * Inicializa el grafo con destinos y tiempos de viaje aleatorios.
     */
    private void initializeMap() {
        // Generación de destinos y vías de comunicación aleatorios (simplificado)
        Random random = new Random();
        List<String> allLocations = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A1", "B2", "C3", "D4");

        // Inicialización el grafo
        for (String location : allLocations) {
            graph.put(location, new HashMap<>());
        }

        // Asigna tiempos de viaje aleatorios entre 1 y 10 segundos
        for (String source : allLocations) {
            for (String destination : allLocations) {
                if (!source.equals(destination)) {
                    graph.get(source).put(destination, random.nextInt(10) + 1);
                }
            }
        }

        // Asigna el recinto de la empresa (companyLocation) a uno de los destinos
        companyLocation = allLocations.get(random.nextInt(allLocations.size()));
    }

    /**
     * Obtiene la lista de destinos disponibles en el grafo.
     *
     * @return Lista de destinos.
     */
    public List<String> getDestinations() {
        return new ArrayList<>(graph.keySet());
    }

    /**
     * Obtiene el tiempo de viaje entre dos destinos en el grafo.
     *
     * @param source      Origen del viaje.
     * @param destination Destino del viaje.
     * @return Tiempo de viaje entre los destinos.
     */
    public int getTravelTime(String source, String destination) {
        return graph.get(source).get(destination);
    }

    /**
     * Calcula la ruta más corta entre dos destinos utilizando el algoritmo de Dijkstra.
     *
     * @param source      Origen de la ruta.
     * @param destination Destino de la ruta.
     * @param forDriver   Indica si se calcula la ruta para un conductor.
     * @return Lista de destinos que forman la ruta más corta.
     */
    public List<String> calculateShortestRoute(String source, String destination, boolean forDriver) {
        // Implementa el algoritmo de Dijkstra para calcular la ruta más corta
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Inicializa distancias y cola de prioridad
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previousNodes.put(node, null);
            priorityQueue.add(node);
        }

        distances.put(source, 0);

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();

            for (String neighbor : graph.get(current).keySet()) {
                int newDistance = distances.get(current) + graph.get(current).get(neighbor);

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, current);
                }
            }
        }

        // Reconstruiye la ruta desde el destino hasta el origen
        List<String> shortestRoute = new ArrayList<>();
        String current = destination;
        while (current != null) {
            shortestRoute.add(current);
            current = previousNodes.get(current);
        }
        Collections.reverse(shortestRoute);

        return shortestRoute;
    }

    /**
     * Calcula la ruta más corta para un pasajero que tiene múltiples destinos.
     *
     * @param source       Origen de la ruta.
     * @param destinations Lista de destinos del pasajero.
     * @return Lista de destinos que forman la ruta más corta para el pasajero.
     */
    public List<String> calculateShortestRouteForPassenger(String source, List<String> destinations) {
        // Implementa el algoritmo de Dijkstra para calcular la ruta más corta para un pasajero
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Inicializa distancias y cola de prioridad
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previousNodes.put(node, null);
            priorityQueue.add(node);
        }

        distances.put(source, 0);

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();

            for (String neighbor : graph.get(current).keySet()) {
                int newDistance = distances.get(current) + graph.get(current).get(neighbor);

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, current);
                }
            }
        }

        // Reconstruye la ruta desde el destino hasta el origen
        List<String> shortestRoute = new ArrayList<>();
        for (String destination : destinations) {
            String current = destination;
            while (current != null) {
                shortestRoute.add(current);
                current = previousNodes.get(current);
            }
        }

        // Ordena la lista para que la ruta sea desde el origen hasta el destino
        Collections.reverse(shortestRoute);

        return shortestRoute;
    }

    /**
     * Obtiene la ubicación de la empresa en el grafo.
     *
     * @return Ubicación de la empresa.
     */
    public String getCompanyLocation() {
        return companyLocation;
    }
    
    /**
     * Registra a un empleado en el sistema.
     *
     * @param employeeId Identificación del empleado.
     * @param residence  Ubicación de residencia del empleado.
     */    
    public void registerEmployee(String employeeId, String residence) {
        
    }

    /**
     * Registra a un conductor en el sistema.
     *
     * @param driverId  Identificación del conductor.
     * @param residence Ubicación de residencia del conductor.
     */    
    public void registerDriver(String driverId, String residence) {
        
    }
    
    /**
     * Obtiene los 5 conductores con más viajes realizados.
     *
     * @return Lista de los 5 conductores con más viajes.
     */    
    public List<String> getTop5Drivers() {
        // Obtenie un mapa con el número de viajes realizados por cada conductor
        Map<String, Integer> driverTripsCount = new HashMap<>();

        // Bueno aquí asumo que existe una estructura que almacena la información de los viajes realizados


        // Aquí por simplicidad, se utilizo un mapa de ejemplo para simular el contador de viajes por conductor
        // El key del mapa es el ID del conductor y el value es el número de viajes realizados
        Map<String, Integer> tripsData = new HashMap<>();
        // Datos de ejemplo
        tripsData.put("Driver1", 10);
        tripsData.put("Driver2", 8);
        tripsData.put("Driver3", 12);
        tripsData.put("Driver4", 6);
        tripsData.put("Driver5", 15);
        tripsData.put("Driver6", 9);

        // Actualiza el mapa driverTripsCount con los datos de viajes realizados
        for (String driverId : tripsData.keySet()) {
            int trips = tripsData.get(driverId);
            driverTripsCount.put(driverId, trips);
        }

        // Obtenie los 5 conductores con más viajes utilizando Insertion Sort
        List<String> top5Drivers = new ArrayList<>(driverTripsCount.keySet());
        for (int i = 1; i < top5Drivers.size(); i++) {
            String currentDriver = top5Drivers.get(i);
            int currentTrips = driverTripsCount.get(currentDriver);
            int j = i - 1;

            while (j >= 0 && driverTripsCount.get(top5Drivers.get(j)) < currentTrips) {
                top5Drivers.set(j + 1, top5Drivers.get(j));
                j--;
            }

            top5Drivers.set(j + 1, currentDriver);
        }

        // Toma los primeros 5 conductores
        if (top5Drivers.size() > 5) {
            top5Drivers = top5Drivers.subList(0, 5);
        }

        return top5Drivers;
    }

    /**
     * Imprime el grafo (mapa) mostrando las conexiones y tiempos de viaje entre destinos.
     */    
    public void printGraph() {
        for (String source : graph.keySet()) {
            System.out.print(source + ": ");
            Map<String, Integer> neighbors = graph.get(source);
            for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                System.out.print("(" + entry.getKey() + ", " + entry.getValue() + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Método principal utilizado para probar los métodos de la clase.
     *
     * @param args Argumentos de línea de comandos (no utilizado en este contexto).
     */
    public static void main(String[] args) {
        // Código de prueba
        CarpoolingServer carpoolingServer = new CarpoolingServer();

        // Imprime la lista de destinos
        System.out.println("Destinos disponibles: " + carpoolingServer.getDestinations());

        // Imprimir el mapa (grafo)
        System.out.println("Mapa (Grafo):");
        carpoolingServer.printGraph();


    }
}


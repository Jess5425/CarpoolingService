import java.util.*;

public class CarpoolingServer {
    private Map<String, Map<String, Integer>> graph; // Grafo que representa las conexiones y tiempos de viaje entre destinos
    private String companyLocation;

    public CarpoolingServer() {
        this.graph = new HashMap<>();
        initializeMap();
    }

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

    public List<String> getDestinations() {
        return new ArrayList<>(graph.keySet());
    }

    public int getTravelTime(String source, String destination) {
        return graph.get(source).get(destination);
    }

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

    public String getCompanyLocation() {
        return companyLocation;
    }
    //Ignacio
    public void registerEmployee(String employeeId, String residence) {
        // Implementar el registro de empleados
    }
    //Ignacio
    public void registerDriver(String driverId, String residence) {
        // Implementar el registro de conductores
    }
    //Ignacio
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
    //Imprime el mapa(grafo)
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
    //Main para probar métodos
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


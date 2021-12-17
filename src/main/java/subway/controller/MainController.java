package subway.controller;

import subway.domain.*;

import java.util.List;

public class MainController {

    StationRepository stationRepository = new StationRepository();
    LineRepository lineRepository = new LineRepository();
    final String[] stations = new String[]{"교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"};
    final String[] lines = new String[]{"2호선", "3호선", "신분당선"};
    final String[] sections = new String[]{
            "교대역,강남역,2,3",
            "강남역,역삼역,2,3",
            "교대역,남부터미널역,3,2",
            "남부터미널역,양재역,6,2",
            "양재역,매봉역,1,1",
            "강남역,양재역,2,8",
            "양재역,양재시민의숲역,10,3"
    };


    public void run() {
        init();
        printStation();
        printLines();
        List<String> shortestPath = Graph.getDijkstraShortestPath("교대역", "양재역");
        Integer distance = 0;
        Integer time = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            for (String section : sections) {
                if (shortestPath.get(i).equals(section.split(",")[0]) && shortestPath.get(i + 1).equals(section.split(",")[1])) {
                    distance += Integer.parseInt(section.split(",")[2]);
                    time += Integer.parseInt(section.split(",")[3]);
                }
            }
        }
        System.out.println(distance.toString());
        System.out.println(time.toString());
        for (String path : shortestPath) {
            System.out.println(path);
        }
    }

    private void init() {
        for (String station : stations) {
            stationRepository.addStation(new Station(station));
            Graph.addVertex(station);
        }

        for (String line : lines) {
            lineRepository.addLine(new Line(line));
        }

        for (String section : sections) {
            String sourceVertex = section.split(",")[0];
            String targetVertex = section.split(",")[1];
            int distanceWeight = Integer.parseInt(section.split(",")[2]);
            int timeWeight = Integer.parseInt(section.split(",")[3]);
            Graph.setEdgeWeight(sourceVertex, targetVertex, distanceWeight, timeWeight);
        }
    }

    private void printStation() {
        for (Station station : StationRepository.stations()) {
            System.out.println(station.getName());
        }
    }

    private void printLines() {
        for (Line line : LineRepository.lines()) {
            System.out.println(line.getName());
        }
    }

}

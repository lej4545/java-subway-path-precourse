package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class MainController {

    StationRepository stationRepository = new StationRepository();
    LineRepository lineRepository = new LineRepository();
    final String[] stations = new String[]{"교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"};
    final String[] lines = new String[]{"2호선", "3호선", "신분당선"};

    public void run() {
        init();
        printStation();
        printLines();
    }

    private void init() {
        for (String station : stations) {
            stationRepository.addStation(new Station(station));
        }

        for (String line : lines) {
            lineRepository.addLine(new Line(line));
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

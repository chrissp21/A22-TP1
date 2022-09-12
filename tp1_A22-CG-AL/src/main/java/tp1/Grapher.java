package tp1;

import javafx.scene.chart.XYChart;

import java.util.List;


public class Grapher {

    public XYChart.Series<Number, Number> createGraph(Parameters params) {

        return null;
    }

    public static class Parameters {

        private List<Double> xList;
        private List<Double> yList;
        private final String name;


        public Parameters(List<Double> xList, List<Double> yList, String name) {
            this.xList = xList;
            this.yList = yList;
            this.name = name;
        }

        public List<Double> getxList() {
            return xList;
        }

        public List<Double> getyList() {
            return yList;
        }

        public String getName() {
            return name;
        }
    }
}

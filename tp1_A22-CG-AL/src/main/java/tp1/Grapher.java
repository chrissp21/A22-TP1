package tp1;

import javafx.scene.chart.XYChart;

import java.util.Iterator;
import java.util.List;


public class Grapher {

    public XYChart.Series<Number, Number> createGraph(Parameters params) {
        XYChart.Series<Number, Number> chart = new XYChart.Series<>();


        Iterator iteratorX = params.getxList().iterator();
        Iterator iteratorY = params.getyList().iterator();

        while(iteratorX.hasNext() && iteratorY.hasNext()){
            chart.getData().add(new XYChart.Data<>((Number) iteratorX.next(),(Number) iteratorY.next()));
        }

        chart.setName(params.getName());
        return chart;
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

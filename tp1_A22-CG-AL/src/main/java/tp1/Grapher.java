package tp1;

import javafx.scene.chart.XYChart;

import java.util.Iterator;
import java.util.List;

/**
 * Grapher pour le Tp1
 *
 * @author Adam Lidam Christophe Guerin
 * @version 1
 */
public class Grapher {
    /**
     * Crée un graphique à partir des paramettres recue
     * @param params les paramettres du graphique
     * @see Parameters
     * @return le graphique genere
     */
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

    /**
     * Class interne de la classe grapher
     */
    public static class Parameters {

        /**
         * List des paramettres en X
         */
        private List<Double> xList;
        /**
         *List des paramettres en Y
         */
        private List<Double> yList;
        /**
         *nom du Graphique
         */
        private final String name;

        /**
         *Construteur de la class Parameters
         */
        public Parameters(List<Double> xList, List<Double> yList, String name) {
            this.xList = xList;
            this.yList = yList;
            this.name = name;
        }

        /**
         * Getter de la liste des paramettres en X
         * @return la list des paramettres en X
         */
        public List<Double> getxList() {
            return xList;
        }

        /**
         * Getter des paramettre en Y
         * @return la list des paramettre en Y
         */
        public List<Double> getyList() {
            return yList;
        }

        /**
         * Getter du nom du graphique
         * @return le nom
         */
        public String getName() {
            return name;
        }
    }
}

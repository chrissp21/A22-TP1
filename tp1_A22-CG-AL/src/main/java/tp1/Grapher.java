package tp1;

import javafx.scene.chart.XYChart;

import java.util.Iterator;
import java.util.List;

/**
 * Class qui génere les graphiques pour le Tp1
 *
 * @author Adam Lidam Christophe Guerin
 * @version 1.0
 */
public class Grapher {
    /**
     * Crée un graphique à partir des parametres reçus
     * @param params les parametres du graphique
     * @see Parameters
     * @return le graphique géneré
     */
    public XYChart.Series<Number, Number> createGraph(Parameters params) {
        //instanciation du graphique
        XYChart.Series<Number, Number> chart = new XYChart.Series<>();

        //Utilisation d'un iterator afin de plus facilement parcourir les lists et de créer le graphique
        Iterator iteratorX = params.getxList().iterator();
        Iterator iteratorY = params.getyList().iterator();

        //Parcours des lists et ajout les points (x et y) dans le graphique
        while(iteratorX.hasNext() && iteratorY.hasNext()){
            chart.getData().add(new XYChart.Data<>((Number) iteratorX.next(),(Number) iteratorY.next()));
        }

        //titre du graphique
        chart.setName(params.getName());
        return chart;
    }

    /**
     * Class interne de la classe grapher
     * créateur et accèsseur des parametres du graphique
     */
    public static class Parameters {

        /**
         * List des parametres en X
         */
        private List<Double> xList;
        /**
         *List des parametres en Y
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
         * accesseur de la liste des parametres en X
         * @return la list des parametres en X
         */
        public List<Double> getxList() {
            return xList;
        }

        /**
         * accesseur des paramettre en Y
         * @return la list des parametres en Y
         */
        public List<Double> getyList() {
            return yList;
        }

        /**
         * accesseur du nom du graphique
         * @return le nom
         */
        public String getName() {
            return name;
        }
    }
}

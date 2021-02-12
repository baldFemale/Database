package toolkit;

/**
 * Created by Jordan on 2017/12/19.
 */

public  class ComboBoxSearch {
        String title;
        String table;
        String attr;

        /**
         * @param title the content of the JLabel
         * @param table The table to select item from.
         * @param attr The attribute to select.
         */
        public ComboBoxSearch(String title, String table, String attr){
            this.title=title;
            this.table=table;
            this.attr=attr;
        }

}

package test;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixPage extends WebPage {

    Form form;
    private final WebMarkupContainer matrixContainer;
    private Integer rowSelection =0;
    private Integer columnSelection = 0;
    public MatrixPage() {


        List sizeChoiceList = Arrays.asList(0,1,2,3,4,5,6,7,8,9);

        Matrix matrix = new Matrix(rowSelection , columnSelection);
        form = new Form("matrixForm");


        Model<Integer> rowModel = new Model<>(rowSelection);
        DropDownChoice rowChoice = new DropDownChoice<Integer>("rowChoice", rowModel, sizeChoiceList);
        form.add(rowChoice);

        Model<Integer> columnModel = new Model<>(columnSelection);
        DropDownChoice columnChoice = new DropDownChoice<Integer>("columnChoice", columnModel, sizeChoiceList);
        form.add(columnChoice);


        Model<String> labelModel = new Model<>();
        Label label = new Label("label", labelModel);
        label.setOutputMarkupId(true);
        form.add(label);


        rowChoice.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                matrix.create(rowModel.getObject(), columnModel.getObject());

                target.add(matrixContainer);
            }
        });
        columnChoice.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                matrix.create(rowModel.getObject(), columnModel.getObject());

                target.add(matrixContainer);
            }
        });
        Model<Matrix> matrixModel = new Model<>();

        matrixModel.setObject(matrix);

        ListView<Row> rows = new ListView<Row>("rows", () -> matrix.getRows()) {

            @Override
            protected void populateItem(ListItem<Row> rowItem) {
                System.out.println("ROWS: "  + matrix.getRows().size());
                Row row = rowItem.getModelObject();
                rowItem.add(new ListView<Integer>("columns", () -> row.getCells()) {
                    @Override
                    protected void populateItem(ListItem<Integer> columnItem) {
                        columnItem.add(new Label("cell", columnItem.getModelObject()));
                    }
                });
                rowItem.add(new Label("sum", row.sum()));
                rowItem.add(new Label("avg", matrix.getColumnAvg(columnModel.getObject() - 1)));

            }
        };

        matrixContainer = new WebMarkupContainer("matrixContainer");
        matrixContainer.setOutputMarkupId(true);
        matrixContainer.add(rows);
        form.add(matrixContainer);
        add(form);
    }
}

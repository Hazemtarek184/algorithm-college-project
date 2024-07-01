import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SortingAlgorithm extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Project GUI");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        Label mLabel = new Label("Enter n:");
        grid.add(mLabel, 0, 0);

        TextField mTextField = new TextField();
        grid.add(mTextField, 1, 0);

        Label nLabel = new Label("Enter m:");
        grid.add(nLabel, 0, 1);

        TextField nTextField = new TextField();
        grid.add(nTextField, 1, 1);

        Label aLabel = new Label("Enter array a:");
        grid.add(aLabel, 0, 2);

        TextField aTextField = new TextField();
        grid.add(aTextField, 1, 2);

        Label bLabel = new Label("Enter array b:");
        grid.add(bLabel, 0, 3);

        TextField bTextField = new TextField();
        grid.add(bTextField, 1, 3);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 0, 4);

        TextArea resultTextArea = new TextArea();
        grid.add(resultTextArea, 0, 5, 2, 1);

        submitButton.setOnAction(e -> {
            // Parsing input: O(1)
            int m = Integer.parseInt(mTextField.getText());
            int n = Integer.parseInt(nTextField.getText());
            int[] a = parseArray(aTextField.getText());
            int[] b = parseArray(bTextField.getText());
            StringBuilder sb = new StringBuilder();

            // Sorting array a: O(n^2)
            bubbleSort(a);

            for (int i = 0; i < n; i++) {
                // Binary search: O(log m)
                int l = 0, r = m - 1, ans = -1;
                while (l <= r) {
                    int mid = l + r >> 1;
                    if (a[mid] <= b[i]) {
                        ans = mid;
                        l = mid + 1;
                    } else r = mid - 1;
                }
                sb.append(ans + 1).append(' ');
            }
            resultTextArea.setText(sb.toString());
        });

        primaryStage.show();
    }

    private int[] parseArray(String input) {
        String[] parts = input.split(" ");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }

    private void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // swap temp and arr[i]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

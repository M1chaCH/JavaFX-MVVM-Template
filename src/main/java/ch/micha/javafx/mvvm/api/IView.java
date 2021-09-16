package ch.micha.javafx.mvvm.api;

/**
 * A blueprint for a View (JavaFX Controller).
 * @param <T> the type of the viewModel for this View. This could be the interface but the you have to parse in the
 *           bind method.
 */
public interface IView<T extends IViewModel> {

    /** bind all changing fxml elements from the View with the Properties in the viewModel. */
    void bind();

    /** the viewModel for this view. */
    void setViewModel(T viewModel);
}

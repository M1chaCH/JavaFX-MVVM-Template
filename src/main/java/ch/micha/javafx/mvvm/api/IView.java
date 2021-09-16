package ch.micha.javafx.mvvm.api;

public interface IView<T extends IViewModel> {

    void bind();
    void setViewModel(T viewModel);
}

package ch.micha.javafx.mvvm.scenes.main;

import ch.micha.javafx.mvvm.api.IView;

public class MainView implements IView<MainViewModel> {
    private MainViewModel viewModel;

    @Override public void bind() {

    }

    @Override public void setViewModel(MainViewModel v) {
        viewModel = v;
    }
}

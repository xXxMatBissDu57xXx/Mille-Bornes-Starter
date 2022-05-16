package nc.unc.gl.borne.gui;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.Route;

@Route
@Tag("borne-main")
public class MainView extends HtmlContainer {
    public MainView() {
        this.add(new CreerJoueur());
    }
}

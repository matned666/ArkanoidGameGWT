package eu.mrndesign.matned.arkanoid.client;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.PERIOD_MILLIS;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Texts.START;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

import eu.mrndesign.matned.arkanoid.client.arkanoid.core.TimeWrapper;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Difficulty;
import eu.mrndesign.matned.arkanoid.client.arkanoid.view.CanvasWidget;

public class MyFirstGWTApplication implements EntryPoint {

	static final String canvasDivTag = "gameCanvas";
	static final String menuDivTag = "gameMenu";
	private CanvasWidget gameWidget;
	private ListBox difficulties;

	public void onModuleLoad() {
		final Timer timer;
		Button button = new Button(START);
		difficulties = new ListBox();
		for (Difficulty d : Difficulty.values()) {
			difficulties.addItem(d.name());
		}
		difficulties.setVisibleItemCount(1);

		RootPanel.get(menuDivTag).add(button);
		RootPanel.get(menuDivTag).add(difficulties);
		timer = new Timer() {
			@Override
			public void run() {
				gameWidget.refreshCanvas();
			}
		};
		TimeWrapper.getInstance().setTimer(timer);
		button.addClickHandler(clickEvent -> {
			if (gameWidget != null)
				RootPanel.get(canvasDivTag).remove(gameWidget);
			timer.scheduleRepeating(PERIOD_MILLIS);
			gameWidget = new CanvasWidget(Difficulty.valueOf(difficulties.getSelectedItemText()));
			RootPanel.get(canvasDivTag).add(gameWidget);

		});
	}

}

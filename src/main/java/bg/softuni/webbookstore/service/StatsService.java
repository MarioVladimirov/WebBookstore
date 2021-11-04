package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}

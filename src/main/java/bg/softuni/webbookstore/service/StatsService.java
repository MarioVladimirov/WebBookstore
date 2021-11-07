package bg.softuni.webbookstore.service;

import bg.softuni.webbookstore.model.view.RequestsStatsView;

public interface StatsService {

    void onRequest();

    RequestsStatsView getStats();
}

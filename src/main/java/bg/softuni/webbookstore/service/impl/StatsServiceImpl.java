package bg.softuni.webbookstore.service.impl;

import bg.softuni.webbookstore.model.view.StatsView;
import bg.softuni.webbookstore.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private int authenticatedRequests, anonymousRequests;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null && (authentication.getPrincipal()) instanceof UserDetails) {
            authenticatedRequests++;
        } else {
            anonymousRequests++;
        }
    }

    @Override
    public StatsView getStats() {
        return new StatsView(authenticatedRequests, anonymousRequests);
    }
}

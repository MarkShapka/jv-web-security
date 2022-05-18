package taxi.service;

import java.util.Optional;
import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driverFromDB = driverService.findByLogin(login);
        if (!driverFromDB.isEmpty() && driverFromDB.get().getPassword().equals(password)) {
            return driverFromDB.get();
        }
        throw new AuthenticationException("Login or password are incorrect");
    }
}

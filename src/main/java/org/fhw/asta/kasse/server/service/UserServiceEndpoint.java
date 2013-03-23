package org.fhw.asta.kasse.server.service;

import org.fhw.asta.kasse.server.common.User;
import org.fhw.asta.kasse.server.component.user.UserComponent;
import org.fhw.asta.kasse.server.dao.UserDao;
import org.fhw.asta.kasse.shared.authentication.AuthenticationResult;
import org.fhw.asta.kasse.shared.model.Person;
import org.fhw.asta.kasse.shared.service.user.UserService;

import com.google.common.base.Optional;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceEndpoint extends RemoteServiceServlet implements UserService {

  private static final long serialVersionUID = 1L;

  // private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceEndpoint.class);

  @Inject
  private UserComponent userComponent;

  @Inject
  private UserDao userDao;

  @Override
  public AuthenticationResult authenticate(String email, String password) {

    final User user = this.userComponent.authenticate(email, password);

    return new AuthenticationResult(user.getAuthenticationStatus());
  }

  @Override
  public Optional<Person> getUserById(String ldapName) {
    return this.userDao.getPersonById(ldapName);
    // TODO Auto-generated method stub
  }

  @Override
  public Optional<Person> getUserByIdAndRevision(String ldapName, int revision) {
    return this.userDao.getPersonByIdAndRevision(ldapName, revision);
  }
}

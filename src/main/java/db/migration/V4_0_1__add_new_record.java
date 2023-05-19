package db.migration;

import java.sql.Connection;
import java.time.LocalDate;
import jdbc_lesson.entities.People;
import jdbc_lesson.repository.BaseRepository;
import jdbc_lesson.repository.RepositoryFactory;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V4_0_1__add_new_record extends BaseJavaMigration {

    @Override
    public void migrate(Context cntxt) throws Exception {
        Connection connection = cntxt.getConnection();
        BaseRepository<People, Long> repository
                = RepositoryFactory.getInstance(connection).of(People.class);
        People people = People.builder().age(25).birthday(LocalDate.of(1998, 1,1)).name("Nick1").build();
        repository.save(people);
    }

}

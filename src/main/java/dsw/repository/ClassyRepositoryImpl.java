package dsw.repository;

import dsw.core.ClassyRepository;
import dsw.repository.implementation.ProjectExplorer;

public class ClassyRepositoryImpl implements ClassyRepository {

    private ProjectExplorer projectExplorer;

    public ClassyRepositoryImpl() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }
}

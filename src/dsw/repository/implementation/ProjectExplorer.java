package dsw.repository.implementation;

import dsw.repository.composite.ClassyNode;
import dsw.repository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(ClassyNode child) {

    }

    @Override
    public void addChild(ClassyNodeComposite child) {
        if(child instanceof Project){
            Project project = (Project) child;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void deleteChild(ClassyNodeComposite child) {
        if(child instanceof Project){
            Project project = (Project) child;
            if(this.getChildren().contains(project)){
                this.getChildren().remove(project);
            }
        }
    }

    @Override
    public void deleteChild(ClassyNode child) {

    }
}

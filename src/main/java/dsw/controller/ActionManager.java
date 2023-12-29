package dsw.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private InfoAction infoAction;
    private ExitAction exitAction;
    private DeleteAction deleteAction;
    private NewProjectAction newProjectAction;
    private NewDiagramAction newDiagramAction;
    private RenameAction renameAction;
    private NewPackageAction newPackageAction;
    private AutorAction autorAction;
    private NewAgregacijaAction agregacijaAction;
    private NewKompozicijaAction kompozicijaAction;
    private NewZavisnostAction zavisnostAction;
    private NewGeneralizacijaAction generalizacijaAction;
    private NewKlasaAction klasaAction;
    private NewInterfejsAction interfejsAction;
    private NewEnumAction enumAction;
    private ZoomOutAction zoomOutAction;
    private ZoomInAction zoomInAction;
    private SelectionAction selectionAction;
    private MoveAction moveAction;
    private DeletePainterAction deletePainterAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SaveTemplateAction saveTemplateAction;
    private SaveAsAction saveAsAction;
    private SaveAction saveAction;
    private TemplateGalleryAction templateGalleryAction;
    private ImportAction importAction;
    private ExportImageAction exportImageAction;
    private AtributAction atributAction;
    private MetodaAction metodaAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        infoAction = new InfoAction();
        exitAction = new ExitAction();
        deleteAction = new DeleteAction();
        newProjectAction = new NewProjectAction();
        newDiagramAction = new NewDiagramAction();
        renameAction = new RenameAction();
        newPackageAction = new NewPackageAction();
        autorAction = new AutorAction();
        agregacijaAction = new NewAgregacijaAction();
        kompozicijaAction = new NewKompozicijaAction();
        zavisnostAction = new NewZavisnostAction();
        generalizacijaAction = new NewGeneralizacijaAction();
        klasaAction = new NewKlasaAction();
        interfejsAction = new NewInterfejsAction();
        enumAction = new NewEnumAction();
        zoomOutAction = new ZoomOutAction();
        zoomInAction = new ZoomInAction();
        selectionAction = new SelectionAction();
        moveAction = new MoveAction();
        deletePainterAction = new DeletePainterAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        saveTemplateAction = new SaveTemplateAction();
        templateGalleryAction = new TemplateGalleryAction();
        importAction = new ImportAction();
        exportImageAction = new ExportImageAction();
        atributAction = new AtributAction();
        metodaAction = new MetodaAction();
    }

}

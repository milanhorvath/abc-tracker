package sk.rain.men.abc.tracking.child;

/**
 * Created by mhorvath on 10.03.2018.
 */

/**
 * Interface to use for the abc child fragments to be determined if a select item was clicked
 */
public interface AbcChildFragment {

    /**
     * Set the selected index of a list item.
     * @param selectedIndex
     * @return id of selected abc data
     */
    Long setSelectedIndex(int selectedIndex);

    /**
     * Enable the save button.
     */
    void enableSaveButton();

    /**
     * Disable the save button.
     */
    void disableSaveButton();
}

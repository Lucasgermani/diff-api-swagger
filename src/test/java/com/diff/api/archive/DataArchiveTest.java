package com.diff.api.archive;

import com.diff.api.resource.ValuesEntry;
import com.diff.api.resource.enums.Direction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;


public class DataArchiveTest {

    ValuesEntry entry;

    @Given("^I am sending \"([^\"]*)\" data to be stored with ID (\\d+) and Data \"([^\"]*)\"$")
    public void iAmSendingDataToBeStoredWithIDAndData(String direction, int id, String value) throws Throwable {
        DataArchive.add(id, value, Direction.findBySideString(direction));
    }

    @When("^I tryed to retrieve the data with id (\\d+)$")
    public void iTryedToRetrieveTheDataWithId(int id) throws Throwable {
        entry = DataArchive.getInstance(id);
    }

    @Then("^should return the exact data \"([^\"]*)\" on \"([^\"]*)\" side$")
    public void shouldReturnTheExactDataOnSide(String value, String side) throws Throwable {
        assertEquals(String.format("'%s' value should be '%s' but is '%s'", side, value, entry.getValue(Direction.findBySideString(side))),
                value, entry.getValue(Direction.findBySideString(side)));
    }
}

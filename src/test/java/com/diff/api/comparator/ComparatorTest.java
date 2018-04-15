package com.diff.api.comparator;

import com.diff.api.resource.Diff;
import com.diff.api.resource.DiffOutput;
import com.diff.api.resource.ValuesEntry;
import com.diff.api.resource.enums.Direction;
import com.diff.api.resource.enums.Result;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ComparatorTest {

    Comparator comparator = new Comparator();
    ValuesEntry entry;
    DiffOutput result;

    @Given("^I am sending an Entry with ID (\\d+) and \"([^\"]*)\" on the left and \"([^\"]*)\" on the right$")
    public void iAmSendingAnEntryWithIDAndOnTheLeftAndOnTheRight(int id, String leftValue, String rightValue) throws Throwable {
        entry = new ValuesEntry(id);
        entry.setLeft(leftValue);
        entry.setRight(rightValue);
    }

    @When("^Comparing them$")
    public void comparingThem() throws Throwable {
        result = comparator.compare(entry);
    }

    @Then("^the result should be \"([^\"]*)\"$")
    public void theResultShouldBe(String expectedResult) throws Throwable {
        assertEquals(Result.valueOf(expectedResult), result.getResult());
    }

    @And("^diff count should be equals to (\\d+)$")
    public void diffCountShouldBeEqualsTo(int count) throws Throwable {
        assertEquals(result.getDiffList().size(), count);
    }

    @And("^should contains diff with offset (\\d+) and lenght (\\d+)$")
    public void shouldContainsDiffWithOffsetAndLenght(int offsetExpected, int lenghtExpected) throws Throwable {
        boolean hasSearchRegistry = false;

        for(Diff diff : result.getDiffList()){
            if(diff.getOffset().equals(offsetExpected) && diff.getLength().equals(lenghtExpected)){
                hasSearchRegistry = true;
                break;
            }
        }
        assertTrue(String.format("Cant find an diff registry with offset='%s' and lenght='%s'", offsetExpected, lenghtExpected), hasSearchRegistry);
    }
}

package org.Files;

import org.Files.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefnition extends BaseClass {

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() {
		navigate("https://www.facebook.com/");

	}

	@When("I complete action")
	public void i_complete_action() {
		

	}

	@Then("I validate the outcomes")
	public void i_validate_the_outcomes() {

	}

}

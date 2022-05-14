@mobile-task @ui
Feature: Validate Zoom App Features
  @mobile-task1 @ui
  Scenario: Validate Join Meeting Feature in Zoom app
    Given User opens zoom app on "android_oneplus_7t"
    And Clicks join meeting
    When User gives meeting id "123456789", toggle video off and joins the meeting
    And put the app in background for 3 seconds and launch it on foreground
    Then Error Message "Invalid meeting ID. Please check and try again." should be visible


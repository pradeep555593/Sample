Meta:
@driver chrome
Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: play youtube video
Given launch browser and navigate to url
And search for $text
When  click on channel link
Then select videos tab
And find video and play $videoName


Examples:
|text|videoName|
|Step-up forum testing conference|STeP-IN SUMMIT 2014 Making|
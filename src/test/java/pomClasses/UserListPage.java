package pomClasses;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utility;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static steps.Base.driver;


public class UserListPage {
    public UserListPage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(2)),this);
    }
    PersonalInformationPage personalInformationPage=new PersonalInformationPage(driver);
    ClinicInformationPage clinicInformationPage=new ClinicInformationPage(driver);
    Logger logger= LoggerFactory.getLogger(UserListPage.class);
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Settings\"]")private WebElement settingsButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Users\"]")private WebElement usersOptionOnsettingsScreen;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Users list\"]")private WebElement usersListScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"fab\"]") private WebElement addButtonOnUsersListScreen;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView)//android.view.ViewGroup[contains(@content-desc, 'Admin')]") private List<WebElement> adminListOnUsersListScreen;
    @AndroidFindBy(xpath = "(//android.widget.ScrollView)//android.view.ViewGroup[contains(@content-desc, 'Doctor')]") private List<WebElement> doctorListOnUserListScreen;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Doctor\"]")private WebElement doctorOptionOnUsersList;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Staff\"]")private WebElement staffOptionOnUsersList;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Date of birth *']/following-sibling::android.view.ViewGroup[@content-desc]\n") private WebElement dobFieldOnPersonalInformation;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']") private WebElement okButtonOnDatePicker;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']") private WebElement cancelButtonOnDatePicker;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Languages known *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement languagesKnownDropdown;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Next\"]") private WebElement nextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Education\"]")private WebElement educationScreenTitle;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"QUALIFICATION\"]") private WebElement addQualification;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='University *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement universityDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Year of graduation *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement yearOfGraduationDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='State *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement stateDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Save\"]") private WebElement saveButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Medical registration\"]") private WebElement medicalRegistrationScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Registration council *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement registrationCouncilDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Registration date *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement registrationDateField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Issue date *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement issueDateField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expiration date *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement expirationDateField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Medical speciality']")private WebElement medicalSpecialityScreenTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Primary specialization *']/following-sibling::android.view.ViewGroup[@content-desc]")private WebElement primarySpecializationDropdown;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Secondary specialization, \uF078\"]")private WebElement secondarySpecializationDropdown;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Treatments specialization, \uF078\"]")private WebElement treatmentsDropdown;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Procedures specialization, \uF078\"]")private WebElement procedureDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address']")private WebElement addressScreenTitle;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"State *\"]/following-sibling::android.view.ViewGroup[@content-desc])[1]")private WebElement stateDropdownOnAddressScreen;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"State *\"]/following-sibling::android.view.ViewGroup[@content-desc])[2]")private WebElement cityDropdownOnAddressScreen;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Privilege']")private WebElement privilegeScreenTitle;




    public void clickOnSettings()
    {
        Utility.explicitlyWait(settingsButton,driver,10);
        settingsButton.click();
        logger.info("Clicked on Settings");
    }
    public void clickOnUsersFeatureOnSettings()
    {
        Utility.explicitlyWait(usersOptionOnsettingsScreen,driver,10);
        usersOptionOnsettingsScreen.click();
        logger.info("Clicked on Users feature present on Settings screen");
    }
    public void verifyUserIsPresentOnUsersListScreen()
    {
        try {
            Utility.explicitlyWait(usersListScreenTitle,driver,10);
            if (usersListScreenTitle.isDisplayed())
            {
                logger.info("User is present on Users list screen");
            }
            else {
                logger.error("User is not present on Users list screen");
            }
        }
        catch (Exception e){
            logger.error("Unexpected exception: " + e.getMessage());
        }
    }
    public void verifyLoggedInAdminIsDisplayed(String expectedNameOfAdmin) throws InterruptedException {
        Thread.sleep(1000);
        boolean isAdminFound=false;
        try{
            for(int i=0;i<adminListOnUsersListScreen.size();i++){
                List<WebElement> adminDetails=adminListOnUsersListScreen.get(i).findElements(By.xpath(".//android.widget.TextView"));
                StringBuilder adminInfo = new StringBuilder("Admin " + (i+1) + ": ");
                for(WebElement detail:adminDetails)
                {
                    adminInfo.append(detail.getText()).append(" | ");
                }
                if(adminInfo.toString().contains(expectedNameOfAdmin))
                {
                    logger.info("Expected Admin Found: "+adminInfo);
                    isAdminFound=true;
                    break;
                }
            }
            if (!isAdminFound) {
                logger.warn("No matching admin found with name: " + expectedNameOfAdmin);
            }
        }
        catch (Exception e)
        {
            logger.error("Unexpected Exception: "+e.getMessage());
        }
    }
    public void verifyOnlyOneAdminIsPresentOnusersScreen(){
        if(adminListOnUsersListScreen.size()==1){
            logger.info("Only one admin is present");
        }
        else {
            logger.error(adminListOnUsersListScreen.size()+" Admins are present");
        }
    }

    public void clickOnAddButtonUsersListScreen() {
        Utility.explicitlyWait(addButtonOnUsersListScreen,driver,10);
        addButtonOnUsersListScreen.click();
        logger.info("User clicked on Add icon");
    }

    public void selectDoctorFromUsersListScreen() {
        Utility.explicitlyWait(doctorOptionOnUsersList,driver,5);
        doctorOptionOnUsersList.click();
        logger.info("User selected the doctor option to add doctor");
    }
    public void selectStaffFromUsersListScreen() {
        Utility.explicitlyWait(staffOptionOnUsersList,driver,5);
        staffOptionOnUsersList.click();
        logger.info("User selected the staff option to add doctor");
    }
    public void selectDOBOnPersonalInformationScreen() throws InterruptedException {
        Utility.explicitlyWait(dobFieldOnPersonalInformation,driver,10);
        dobFieldOnPersonalInformation.click();
//        WebElement yearPicker = driver.findElement(By.xpath("(//android.widget.LinearLayout[@resource-id=\"android:id/pickers\"]/android.widget.NumberPicker[1]//android.widget.Button)[1]"));
//        swipeUp(yearPicker.getLocation().getY() + 200, yearPicker.getLocation().getY() - 300); // Swipe up
        Utility.explicitlyWait(okButtonOnDatePicker,driver,10);
        okButtonOnDatePicker.click();

    }
    public void swipeUp(int startY, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 300, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 300, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
    public void selectLanguagesKnown(List<String> languagesList)
    {
        Utility.explicitlyWait(languagesKnownDropdown,driver,10);
        languagesKnownDropdown.click();
        for (String language : languagesList) {
            WebElement languageValue = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+language+"')]"));
            Utility.explicitlyWait(languageValue, driver, 10);
            languageValue.click();
            logger.info("Language selected: " + languageValue.getText());
        }
    }
    public void clickOnBackButtonToCloseDropdown() throws InterruptedException {
        ((PressesKey)driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        logger.info("Clicked on back button to close dropdown");
    }
    public void clickOnNext()
    {
        Utility.swipeDown(driver);
        Utility.explicitlyWait(nextButton,driver,10);
        nextButton.click();
        logger.info("Clicked on Next button");
    }
    public void verifyUserIsOnEducationScreenAndClickOnAddQualification()
    {
        Utility.explicitlyWait(educationScreenTitle,driver,10);
        if (educationScreenTitle.isDisplayed())
        {
            logger.info("User is present on Education screen");
        }
        else {
            logger.error("User is not present on Education screen");
        }
        Utility.explicitlyWait(addQualification,driver,10);
        addQualification.click();
    }
    public void selectUniversity(String expectedUniversityName)
    {
        Utility.explicitlyWait(universityDropdown,driver,10);
        universityDropdown.click();
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedUniversityName+"')]")).click();
        logger.info("University selected: "+expectedUniversityName);
    }
    public void selectYearOfGraduation(String expectedYearOfGraduation)
    {
        Utility.explicitlyWait(yearOfGraduationDropdown,driver,10);
        yearOfGraduationDropdown.click();
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedYearOfGraduation+"')]")).click();
        logger.info("Year of graduation selected: "+expectedYearOfGraduation);
    }
    public void selectState(String expectedStateName){
        Utility.explicitlyWait(stateDropdown,driver,10);
        stateDropdown.click();
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedStateName+"')]")).click();
        logger.info("State selected: "+expectedStateName);
    }
    public void clickOnSave()
    {
        Utility.explicitlyWait(saveButton,driver,10);
        saveButton.click();
        logger.info("Clicked on save");
    }
    public  void  enterQualificationAndSave() throws InterruptedException {
        clinicInformationPage.selectOptions(Collections.singletonList("Graduation"));
        personalInformationPage.sendInputToField("Course*","MBBS");
        selectUniversity("Annamalai University");
        selectYearOfGraduation("2021");
        selectState("Assam");
        clickOnSave();
        clickOnNext();
    }
    public void verifyUserIspresentOnMedicalRegistrationScreen()
    {
        Utility.explicitlyWait(medicalRegistrationScreenTitle,driver,10);
        if(medicalRegistrationScreenTitle.isDisplayed())
        {
            logger.info("User is present on Medical Registration screen while doctor onboarding");
        }
        else {
            logger.error("User is not present on Medical registration screen");
        }
    }
    public void selectRegistrationCouncil(String expectedRegistrationCouncil) throws InterruptedException {
        Utility.explicitlyWait(registrationCouncilDropdown,driver,10);
        registrationCouncilDropdown.click();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(expectedRegistrationCouncil);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedRegistrationCouncil+"')]")).click();
        logger.info("Registration council selected: "+expectedRegistrationCouncil);
    }
    public void selectRegistrationDate()
    {
        Utility.explicitlyWait(registrationDateField,driver,10);
        registrationDateField.click();
        okButtonOnDatePicker.click();
        logger.info("Registration date is selected");
    }
    public void selectIssueDate()
    {
        Utility.explicitlyWait(issueDateField,driver,10);
        issueDateField.click();
        okButtonOnDatePicker.click();
        logger.info("Issue date is selected");
    }
    public void selectExpirationDate()
    {
        Utility.explicitlyWait(expirationDateField,driver,10);
        expirationDateField.click();
        okButtonOnDatePicker.click();
        logger.info("Expiration date is selected");
    }
    public void enterMedicalRegistrationAndNavigatesToNext() throws InterruptedException {
        personalInformationPage.sendInputToField("Registration number*","Reg1122");
        selectRegistrationCouncil("Assam");
        selectRegistrationDate();
        selectIssueDate();
        selectExpirationDate();
        clickOnNext();
    }
    public void selectPrimarySpecialization(String expectedPrimarySpecialization) throws InterruptedException {
        Utility.explicitlyWait(primarySpecializationDropdown,driver,10);
        primarySpecializationDropdown.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(expectedPrimarySpecialization);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedPrimarySpecialization+"')]")).click();
        logger.info("Primary specialization selected: "+expectedPrimarySpecialization);
    }
    public void selectSecondarySpecialization(List<String> expectedSecondarySpecializations) throws InterruptedException {
        Utility.explicitlyWait(secondarySpecializationDropdown,driver,10);
        secondarySpecializationDropdown.click();
        Thread.sleep(1000);
        for (String specialization : expectedSecondarySpecializations) {
            WebElement specializationValue = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+specialization+"')]"));
            Utility.explicitlyWait(specializationValue, driver, 10);
            specializationValue.click();
            logger.info("Secondary specialization selected: " + specializationValue.getText());
        }
    }
    public void selectTreatments(String expectedTreatments) throws InterruptedException {
        Utility.explicitlyWait(treatmentsDropdown,driver,10);
        treatmentsDropdown.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(expectedTreatments);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedTreatments+"')]")).click();
        logger.info("Treatments selected: "+expectedTreatments);
    }
    public void selectProcedures(String expectedProcedures) throws InterruptedException {
        Utility.explicitlyWait(procedureDropdown,driver,10);
        procedureDropdown.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(expectedProcedures);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedProcedures+"')]")).click();
        logger.info("Procedures selected: "+expectedProcedures);
    }
    public void verifyUserIsPresentOnMedicalSpecialityScreen(){
        Utility.explicitlyWait(medicalSpecialityScreenTitle,driver,10);
        if (medicalSpecialityScreenTitle.isDisplayed())
        {
            logger.info("User is present on Medical speciality screen");
        }
        else{
            logger.error("User is not present on Medical speciality screen");
        }
    }
    public void enterMedicalSpecialityAndNavigatesToNext() throws InterruptedException {
        selectPrimarySpecialization("Cardiology");
        selectSecondarySpecialization(Arrays.asList("Accident & Emergency","Aesthetic Dermatology"));
        clickOnBackButtonToCloseDropdown();
        personalInformationPage.sendInputToField("Years of experience*","3");
        selectTreatments("Acupuncture");
        clickOnBackButtonToCloseDropdown();
        selectProcedures("Fluoroscopy");
        clickOnBackButtonToCloseDropdown();
        personalInformationPage.sendInputToField("Area of expertize","Expert in cardiac science");
        personalInformationPage.sendInputToField("Short description*", "Hi, I am working in the field of cadiology from last few years");
        clickOnNext();
    }
    public void verifyUserIsPresentOnAddressScreen()
    {
        Utility.explicitlyWait(addressScreenTitle,driver,10);
        if(addressScreenTitle.isDisplayed())
        {
            logger.info("User is present on Address screen");
        }
        else {
            logger.error("User is not present on Address screen");
        }
    }
    public void selectStateOnAddressScreen(String expectedStateName) throws InterruptedException {
        Utility.explicitlyWait(stateDropdownOnAddressScreen,driver,10);
        stateDropdownOnAddressScreen.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(expectedStateName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedStateName+"')]")).click();
        logger.info("State selected: "+expectedStateName);
    }
    public void selectCityOnAddressScreen(String expectedCityName) throws InterruptedException {
        Utility.explicitlyWait(cityDropdownOnAddressScreen,driver,10);
        cityDropdownOnAddressScreen.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(expectedCityName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+expectedCityName+"')]")).click();
        logger.info("City selected: "+expectedCityName);
    }
    public void enterAddressAndNavigatesToNext() throws InterruptedException {
        personalInformationPage.sendInputToField("Address line-1*","H121");
        personalInformationPage.sendInputToField("Street address/locality","Metro street");
        personalInformationPage.sendInputToField("Landmark", "Near Ram temple");
        selectStateOnAddressScreen("Telangana");
        selectCityOnAddressScreen("Madhapur");
        personalInformationPage.sendInputToField("Pincode*","500081");
        clickOnNext();
    }
    public void verifyUserIsPresentOnPrivilegesScreen(){
        Utility.explicitlyWait(privilegeScreenTitle,driver,10);
        if(privilegeScreenTitle.isDisplayed()){
            logger.info("User is present on Privilege screen");
        }
        else {
            logger.error("User is not present on Privilege screen");
        }
    }
    public boolean isEnabledPrivilege(String expectedPrivilege)
    {
        try {
            WebElement privilegeToggleButton=driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[@text='" + expectedPrivilege + "']/following-sibling::android.widget.Switch"));
            String toggleText = privilegeToggleButton.getText().trim().toLowerCase();
            if (toggleText.equals("on")) {
                logger.info(expectedPrivilege + " is enabled for the user");
                return true;
            } else {
                logger.info(expectedPrivilege + " is disabled for the user");
                return false;
            }
        } catch (Exception e) {
           logger.error("Unexpected exception: "+e.getMessage());
           return false;
        }
    }
    public void enablePrivilege(String expectedPrivilege)
    {
        if(isEnabledPrivilege(expectedPrivilege))
        {
            logger.info(expectedPrivilege+" Privilege is already enabled");
        }
        else
        {
            WebElement privilegeToggleButton=driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[@text='" + expectedPrivilege + "']/following-sibling::android.widget.Switch"));
            privilegeToggleButton.click();
            if (isEnabledPrivilege(expectedPrivilege))
            {
                logger.info(expectedPrivilege+" Privilege is enabled");
            }
            else {
                logger.info(expectedPrivilege+" Privilege cannot be enabled");
            }
        }
    }
    public void disablePrivilege(String expectedPrivilege)
    {
        if(isEnabledPrivilege(expectedPrivilege)){
            WebElement privilegeToggleButton=driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[@text='" + expectedPrivilege + "']/following-sibling::android.widget.Switch"));
            privilegeToggleButton.click();
            if (!isEnabledPrivilege(expectedPrivilege))
            {
                logger.info(expectedPrivilege+" Privilege is disabled");
            }
            else {
                logger.info(expectedPrivilege+" Privilege cannot be disabled");
            }
        }
        else {
            logger.info(expectedPrivilege+" Privilege is already disabled");
        }
    }
    public void verifyPrivilegeToggle(String expectedPrivilege, boolean isToggleAllowed, boolean expectedStateAfterToggle)
    {
        try {
            // Step 1: Check if the privilege is currently enabled or disabled
            boolean initialState = isEnabledPrivilege(expectedPrivilege);
            logger.info("Initial state of '" + expectedPrivilege + "' is: " + (initialState ? "Enabled" : "Disabled"));

            // Step 2: If toggling is allowed, try to change the state
            if (isToggleAllowed) {
                WebElement privilegeToggleButton = driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[@text='"
                        + expectedPrivilege + "']/following-sibling::android.widget.Switch"));

                privilegeToggleButton.click(); // Toggle the switch
                Thread.sleep(2000); // Wait for state change (better to use explicit wait)

                // Step 3: Verify if the state changed successfully
                boolean newState = isEnabledPrivilege(expectedPrivilege);
                if (newState == expectedStateAfterToggle) {
                    logger.info("Successfully toggled '" + expectedPrivilege + "' to: " + (newState ? "Enabled" : "Disabled"));
                } else {
                    logger.error("Failed to toggle '" + expectedPrivilege + "'. Expected: " + expectedStateAfterToggle + ", but got: " + newState);
                }
            } else {
                logger.info("Toggle action is not allowed for '" + expectedPrivilege + "' based on the user role.");

                // Step 4: Try clicking to verify that it does not change
                WebElement privilegeToggleButton = driver.findElement(By.xpath("//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[@text='"
                        + expectedPrivilege + "']/following-sibling::android.widget.Switch"));

                privilegeToggleButton.click();
                Thread.sleep(1000);

                boolean newState = isEnabledPrivilege(expectedPrivilege);
                if (newState == initialState) {
                    logger.info("Privilege state remained unchanged as expected.");
                } else {
                    logger.error("Privilege state changed unexpectedly!");
                }
            }
        } catch (Exception e) {
            logger.error("Error while verifying privilege toggle for '" + expectedPrivilege + "': " + e.getMessage());
        }
    }

    public void verifyDoctorPrivileges(){
        verifyPrivilegeToggle("Appointment management",true,false);
        verifyPrivilegeToggle("Packages",false,false);
    }



}

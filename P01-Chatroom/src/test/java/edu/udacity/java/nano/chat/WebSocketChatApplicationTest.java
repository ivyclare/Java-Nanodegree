package edu.udacity.java.nano.chat;

import edu.udacity.java.nano.WebSocketChatApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebSocketChatApplication.class)
public class WebSocketChatApplicationTest {

    @LocalServerPort
    private int port;

    @Test
    public void testChatRoom() throws InterruptedException {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "//webdrivers//chromedriver");
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 2);
        webDriver.get("http://localhost:" + port);
        webDriver.manage().window().maximize();

        //Test "JOIN"
        webDriver.findElement(By.id("username")).sendKeys("George");
        webDriver.findElement(By.linkText("Login")).click();
        Thread.sleep(2000);
        if (webDriver.getTitle().equals("Chat Room")) {
            System.out.println("ChatRoomTest 'JOIN' successful!");
        } else {
            System.out.println("ChatRoomTest 'JOIN' failed!");
        }

        //Test "CHAT"
        webDriver.findElement(By.id("msg")).sendKeys("chatbot");
        webDriver.findElement(By.id("sendMessage")).click();
        Thread.sleep(2000);
        if (webDriver.findElement(By.className("message-content")).getText().equalsIgnoreCase("George：chatbot")) {
            System.out.println("ChatRoomTest 'CHAT' successful!");
        } else {
            System.out.println("ChatRoomTest 'CHAT' failed!");
        }

        //Test "LEAVE"
        webDriver.findElement(By.partialLinkText("exit_to_app")).click();
        Thread.sleep(2000);
        if (webDriver.getTitle().equals("Chat Room Login")) {
            System.out.println("ChatRoomTest 'LEAVE' successful!");
        } else {
            System.out.println("ChatRoomTest 'LEAVE' failed!");
        }

        webDriver.close();
    }
}

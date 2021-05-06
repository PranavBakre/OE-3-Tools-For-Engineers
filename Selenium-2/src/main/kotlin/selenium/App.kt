package selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver
import java.net.URL
import java.net.URLEncoder
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.Scanner

fun main() {
    //"/home/psbakre/Downloads/geckodriver-v0.29.0-linux64/geckodriver")
    val driverPath = {}.javaClass.getResource("/selenium/geckodriver")!!.path
    System.setProperty("webdriver.gecko.driver", driverPath)

    println("Enter the search term")
    val search = readLine()

    println("Enter the min year")
    val minYear = readLine() ?: ""

    val driver = FirefoxDriver()
    val baseUrl = "https://scholar.google.com/scholar?q="
    driver.get("$baseUrl${URLEncoder.encode(search, "UTF-8")}&as_ylo=$minYear")
    var count=0
    Files.createDirectory(Path.of("Files"))
    driver.findElements(By.ByXPath("//div[@class=\"gs_r gs_or gs_scl\"]/div/div/div/a"))
        .filter { element -> element.getAttribute("href").toString().endsWith("pdf") }
        .forEach { element ->
            println(element.getAttribute("href"))
            val url = URL(element.getAttribute("href"))
            url.openStream().use{ stream->
                println(Path.of("Files","$count.pdf").fileName)
                Files.copy(stream, Path.of("Files","$count.pdf"),StandardCopyOption.REPLACE_EXISTING)
            }
            count++
        }

//    val sc = Scanner(System.`in`)
//    val email:String? = sc.nextLine()
//    println(email)
//    val password:String? = System.console().readPassword("Enter password").toString()
//    println(password)
//    System.setProperty("webdriver.gecko.driver",
//    //"/home/psbakre/Downloads/geckodriver-v0.29.0-linux64/geckodriver")
//    {}.javaClass.getResource("/selenium/geckodriver").path)
//    val driver: WebDriver = FirefoxDriver()
//    val baseUrl: String = "https://google.com"
//    driver.get(baseUrl)
//    driver.findElement(By.xpath("//a[@class='gb_4 gb_5 gb_ae gb_4c']")).click()
//    driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys()
//    println(driver.getTitle())

}

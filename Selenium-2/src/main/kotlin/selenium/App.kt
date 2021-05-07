package selenium

import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver
import java.net.URL
import java.net.URLEncoder
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

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
    val finalUrl = "$baseUrl${URLEncoder.encode(search, "UTF-8")}&as_ylo=$minYear"

    driver.use{
        it.get("$baseUrl${URLEncoder.encode(search, "UTF-8")}&as_ylo=$minYear")
        var count=0
        Files.createDirectory(Path.of("Files"))
        it.findElements(By.ByXPath("//div[@class=\"gs_r gs_or gs_scl\"]/div/div/div/a"))
            .filter { element -> element.findElement(By.className("gs_ctg2")).text=="[PDF]" }
            .forEach { element ->
                try {
                    if (driver.currentUrl != finalUrl) {
                        driver.navigate().back()
                    }
                    val aTag = element.getAttribute("href")
                    println(aTag)
                    val url = URL(aTag);
                    if (aTag.endsWith(".pdf")) {
                        url.openStream().use { stream ->
                            println(Path.of("Files", "$count.pdf").fileName)
                            Files.copy(stream, Path.of("Files", "$count.pdf"), StandardCopyOption.REPLACE_EXISTING)
                        }
                    }
                    count++
                } catch(exception: Exception) {
                    println(exception.message)
                }
            }
    }

}

private fun FirefoxDriver.use(block: (FirefoxDriver) -> Unit) {
//    try {
//        block(this)
//    }catch(exception: Exception) {

//    }
//    finally {
//        this.close();
//    }
    block(this)
    this.close()
}

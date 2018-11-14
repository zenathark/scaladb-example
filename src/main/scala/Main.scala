import scala.swing._
import com.zenathark.reservation._
import scala.swing.event.ValueChanged

object Main extends SimpleSwingApplication {

  def top = new MainFrame {
    title = "ClassRoom Reservation"
    preferredSize = new Dimension(320, 240)
    contents = view.UsersPanelFactory.getPanel
  }
}

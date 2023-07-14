import reactlogo from "./assets/reactlogo.jpg"
import springlogo from "./assets/springlogo.png"
export default function Footer() {

    return(
        <footer className="footer pt-5 pb-3 ">
            <div className="content has-text-centered ">
                <p>
                    <strong>Dietapp</strong><a href="https://www.linkedin.com/in/alan-barrientos-041026239/"> by Alan
                    Barrientos.</a>
                </p>
                <p className="mt-0 mb-0 mr- pb-2 is-inline">Powered by:</p>
                <img style={{height: "20px"}} src={reactlogo} className="is-inline mr-3 ml-3"/>
                <img style={{height: "15px"}} src={springlogo} className="mr-3 ml-3"/>
                <img src={"https://bulma.io/images/bulma-logo.png"} style={{height: "15px"}}/>
            </div>
        </footer>
    )
}

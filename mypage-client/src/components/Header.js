import "../assets/css/Header.scss";
import { LoremIpsum } from "react-lorem-ipsum";
import { useState, useEffect } from "react";
import Lottie from "lottie-react";
import animationData from "../assets/icon/Animation - 1731823369893.json";
import { useNavigate } from "react-router-dom";
import ResearchAndEtcModal from "./ResearchAndEtcModal";

const Header = () => {
  const navigate = useNavigate();
  const [sideHeaderOpen, setSideHeaderOpen] = useState(null);
  const [etcModalOpen, setEtcModalOpen] = useState(null);
  
  const opnSideHeader = () => {
    if(window.innerWidth < 1100)
    setSideHeaderOpen(!sideHeaderOpen);
  }

  const checkWindowSize = () => {
    if (window.innerWidth >= 1100) {
      setSideHeaderOpen(true);
    } else {
      setSideHeaderOpen(false);
    }
  };

  useEffect(() => {
    checkWindowSize(); 
    window.addEventListener("resize", checkWindowSize);
    return () => {
      window.removeEventListener("resize", checkWindowSize); 
    };
  }, []);

  const openSearchAndEtcInfoModal = () => {
    setEtcModalOpen(true);
  }
  const etcLinkgo = () => {
    navigate();
  }
  return (
    <header>
       <div className={`side-header ${sideHeaderOpen ? "show" : ""}`}>
            <div className="myinfo">
                <div className="myicon"></div>
                <div className="headersBackgroundPicture"></div>
                <div className="myNickName">parkseyoung1209@gmail.com</div>
            </div>
            <div className="contents-wrapper">
                <div className="contents">
                </div>
            </div>
       </div>
      <div className={`bar-header ${sideHeaderOpen !== null && sideHeaderOpen === true? "showbar" : ""}`}>
        <div className="bar-contents">
            <p className="side-header-open-icon" onClick={opnSideHeader}>
            <Lottie animationData={animationData} loop={true} />
            </p>
            <p className="header-title"
            style={sideHeaderOpen !== null && sideHeaderOpen === true ? { marginLeft : 'calc(25% - 70px)', marginRight : 'calc(25% - 125px)'
            } : {marginLeft : 'calc(25% + 70px)'}}
            >자바개발자 박세영</p>
            <p className="etc-post"
            style = {sideHeaderOpen === false ? {marginRight : '10px'} : {marginLeft : '25px'}}
            onClick={openSearchAndEtcInfoModal}
            >More</p>
        </div>
      </div>
      {etcModalOpen === true ? (
        <ResearchAndEtcModal setEtcModalOpen={setEtcModalOpen}></ResearchAndEtcModal>
      ) : <></>}
    </header>
  );
};

export default Header;

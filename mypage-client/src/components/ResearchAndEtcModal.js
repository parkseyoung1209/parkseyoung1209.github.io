import { useState } from "react";
const ResearchAndEtcModal = ({setEtcModalOpen}) => {
   
    const exitModal = () => {
        setEtcModalOpen(false);
    }
    return (
        <>
            <div className="modal-background"
            onClick={exitModal}
            >
                <div className="etc-modal-container">
                    
                </div>
            </div>
        </>
    )
}

export default ResearchAndEtcModal;
import "../assets/css/Header.scss";
import { LoremIpsum } from 'react-lorem-ipsum';

const Header = () => {
    return (
        <>
        <header>
                <div className="myinfo">
                    <div className="myicon"></div>
                    <div className="headersBackgroundPicture"></div>
                    <div className="myNickName">
                        개발자 박세영
                        parkseyoung1209@gmail.com
                    </div>
                </div>
                <div className="contents-wrapper">
                    <div className="contents">
                        전체 목록
                        컴퓨터 구조
                        운영체제
                        프로그래밍 언어
                        네트워크
                        프레임워크
                        DB
                        서버
                        자료구조 && 알고리즘
                        <LoremIpsum p={7} />
                    </div>
                </div>
        </header>
        </>
    );
};

export default Header;
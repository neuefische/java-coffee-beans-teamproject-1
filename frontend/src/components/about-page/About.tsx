import { Container } from 'react-bootstrap';

const About = () => {
    return (
        <Container className="mt-5 mb-5">
            <h1 className="text-center mb-4 about-header">About Us</h1>
            <div className="about-section">
                <h2 className="mt-4">Grocery Shopping App</h2>
                <p>
                    Our Grocery app is the perfect solution to better organize your next shopping trip and keep track of your purchases. Effortlessly record, manage, and explore all your
                    shopping lists and individual items. Our Grocery shopping app makes your next shopping trip easier through planning and organization!
                    Discover, review, and plan your shopping in a new way with our Grocery shopping app!
                </p>
            </div>
            <div className="about-section">
                <h2 className="mt-4">Our Vision</h2>
                <p>
                    Our vision is to be a leader in the industry, recognized for our commitment to excellence and customer satisfaction.
                    We strive to push the boundaries of what’s possible and inspire others to do the same.
                </p>
            </div>
            <div className="about-section">
                <h2 className="mt-4">Our Values</h2>
                <ul className="about-values">
                    <li>Innovation</li>
                    <li>Integrity</li>
                    <li>Community Engagement</li>
                    <li>Customer Focus</li>
                </ul>
            </div>
            <div className="about-section">
                <h2 className="mt-4">Get in Touch</h2>
                <p>
                    We’d love to hear from you! Please reach out if you have any questions or feedback.
                    Your insights help us grow and improve.
                </p>
            </div>
        </Container>
    );
};

export default About;

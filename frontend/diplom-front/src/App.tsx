import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import ErrorBoundary from "./components/ErrorBoundary";
import Header from "./components/general/Header";
import Footer from "./components/general/Footer";
import ManagePage from "./pages/ManagePage";
import FinOperationForecastPage from "./pages/ForecastPage";

const App: React.FC = () => {
  return (
    <Router>
      <Header />
      <ErrorBoundary>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/manage" element={<ManagePage />} />
          <Route path="/forecast" element={<FinOperationForecastPage />} />
        </Routes>
      </ErrorBoundary>
      <Footer />
    </Router>
  );
};

export default App;

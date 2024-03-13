import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { MemoryRouter } from 'react-router-dom';
import ATBrowser from "./ATBrowser";

// Mock useNavigate globally
const mockedUseNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useNavigate: () => mockedUseNavigate,
}));

describe('ATBrowser', () => {
    beforeEach(() => {
        render(
            <MemoryRouter>
                <ATBrowser />
            </MemoryRouter>
        );
    });

    test('searches for the airport "MEX"', () => {
        const airportInput = screen.getByPlaceholderText('Search Airport by ID');

        // Clear the input field before changing the value
        fireEvent.change(airportInput, { target: { value: 'MEX' } });

        // Simulate clicking the 'Browse' button
        const browseButton = screen.getByText('Browse', { selector: '.rectangle-11-container .button' });
        fireEvent.click(browseButton);

        // Check if the mock was called with the correct arguments
        expect(mockedUseNavigate).toHaveBeenCalledWith('/ATAirportPage', {
            replace: true,
            state: { IATA: 'MEX' },
        });
    });

    // Add more tests as needed
});

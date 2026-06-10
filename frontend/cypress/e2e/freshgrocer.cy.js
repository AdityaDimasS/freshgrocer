describe('FreshGrocer End-to-End Test', () => {

    it('Berhasil melakukan pemesanan', () => {

        cy.visit('http://localhost:8000');

        cy.get('#userId')
            .type('U001');

        cy.get('#tier')
            .select('GOLD');

        cy.get('#productId')
            .select('P001');

        cy.get('#quantity')
            .type('5');

        cy.get('#duration')
            .type('6');

        cy.get('#address')
            .type('Jl. Diponegoro, Ponorogo');

        cy.get('#submitBtn')
            .click();

        cy.get('#success-msg')
            .should('be.visible')
            .and('contain', 'Pesanan Berhasil');

        cy.get('#res-total')
            .should('be.visible');

        cy.get('#res-discount')
            .should('be.visible');

        cy.get('#res-delivery')
            .should('be.visible');

        cy.get('#res-address')
            .should('contain', 'Jl. Diponegoro, Ponorogo');
    });

});
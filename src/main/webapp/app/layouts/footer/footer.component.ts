import { Component } from '@angular/core';
import { currentId } from 'async_hooks';

@Component({
    selector: 'jhi-footer',
    templateUrl: './footer.component.html'
})
export class FooterComponent {
    currentYear: number;
    constructor() {
        this.currentYear = new Date().getFullYear();
    }
}

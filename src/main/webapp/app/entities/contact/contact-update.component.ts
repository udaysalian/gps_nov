import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IContact } from 'app/shared/model/contact.model';
import { ContactService } from './contact.service';
import { IUser, UserService } from 'app/core';
import { IBusinessAssociate } from 'app/shared/model/business-associate.model';
import { BusinessAssociateService } from 'app/entities/business-associate';

@Component({
    selector: 'jhi-contact-update',
    templateUrl: './contact-update.component.html'
})
export class ContactUpdateComponent implements OnInit {
    private _contact: IContact;
    isSaving: boolean;

    users: IUser[];

    businessassociates: IBusinessAssociate[];
    updateTimestamp: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private contactService: ContactService,
        private userService: UserService,
        private businessAssociateService: BusinessAssociateService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contact }) => {
            this.contact = contact;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.businessAssociateService.query().subscribe(
            (res: HttpResponse<IBusinessAssociate[]>) => {
                this.businessassociates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.contact.updateTimestamp = moment(this.updateTimestamp, DATE_TIME_FORMAT);
        if (this.contact.id !== undefined) {
            this.subscribeToSaveResponse(this.contactService.update(this.contact));
        } else {
            this.subscribeToSaveResponse(this.contactService.create(this.contact));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContact>>) {
        result.subscribe((res: HttpResponse<IContact>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackBusinessAssociateById(index: number, item: IBusinessAssociate) {
        return item.id;
    }
    get contact() {
        return this._contact;
    }

    set contact(contact: IContact) {
        this._contact = contact;
        this.updateTimestamp = moment(contact.updateTimestamp).format(DATE_TIME_FORMAT);
    }
}

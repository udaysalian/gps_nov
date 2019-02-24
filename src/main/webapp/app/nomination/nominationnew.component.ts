import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/forkJoin';

import { ColDef, ColumnApi, GridApi } from 'ag-grid';
import { NominationService } from 'app/entities/nomination';

import { INomination, Nomination, NomRequestStatus } from 'app/shared/model/nomination.model';
import { Activity } from 'app/shared/model/activity.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiAlertService } from 'ng-jhipster';
import { INominationPriority } from 'app/shared/model/nomination-priority.model';
import { Moment } from 'moment';

@Component({
    selector: 'jhi-nominationnew',
    templateUrl: './nominationnew.component.html',
    styleUrls: ['nominationnew.scss']
})
export class NominationNewComponent implements OnInit {
    rowData: INomination[];
    columnDefs: ColDef[];

    // gridApi and columnApi
    private api: GridApi;
    private columnApi: ColumnApi;

    editInProgress: boolean = false;

    private nominationBeingEdited: Nomination = null;
    private containerCoords: {} = null;

    @ViewChild('grid', { read: ElementRef })
    public grid;
    message: string;

    // inject the athleteService
    constructor(private nominationService: NominationService, private jhiAlertService: JhiAlertService) {
        this.message = 'NominationComponent message';
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    getRowNodeId(params) {
        return params.id;
    }

    ngOnInit() {
        this.nominationService.query().subscribe(
            (noms: HttpResponse<INomination[]>) => {
                this.rowData = noms.body;

                this.columnDefs = this.createColumnDefs();
            },
            (res: HttpErrorResponse) => this.onError(res.message)
            //error => console.log(error)
        );
    }

    onNominationSaved(nomToSave: Nomination) {
        this.nominationService.update(nomToSave).subscribe(
            savedNom => {
                console.log('Noimination saved', savedNom);

                const added = [];
                const updated = [];
                if (nomToSave.id) {
                    updated.push(savedNom);
                } else {
                    added.push(savedNom);
                }

                this.api.updateRowData({
                    add: added,
                    update: updated
                });
            },
            error => console.log(error)
        );

        this.nominationBeingEdited = null;
        this.editInProgress = false;
    }

    // one grid initialisation, grab the APIs and auto resize the columns to fit the available space
    onGridReady(params): void {
        this.api = params.api;
        this.columnApi = params.columnApi;

        this.api.sizeColumnsToFit();
    }

    // create some simple column definitions
    private createColumnDefs() {
        return [
            {
                field: 'gasDate',
                editable: false
            },
            {
                field: 'contractContrId',
                editable: false
            },
            {
                field: 'activityActivityNbr',
                editable: false
            },
            {
                field: 'activityActivityNbr',
                editable: false
            },

            {
                field: 'requestedRcptQty',
                editable: true
            }
        ];
    }

    insertNewRow() {
        this.updateContainerCoords();
        this.editInProgress = true;
    }

    private updateContainerCoords() {
        this.containerCoords = {
            top: this.grid.nativeElement.offsetTop,
            left: this.grid.nativeElement.offsetLeft,
            height: this.grid.nativeElement.offsetHeight,
            width: this.grid.nativeElement.offsetWidth
        };
    }

    rowsSelected() {
        return this.api && this.api.getSelectedRows().length > 0;
    }

    deleteSelectedRows() {
        const selectRows = this.api.getSelectedRows();

        // create an Observable for each row to delete
        const deleteSubscriptions = selectRows.map(rowToDelete => {
            return this.nominationService.delete(rowToDelete);
        });

        /* // then subscribe to these and once all done, update the grid
        Observable.forkJoin(...deleteSubscriptions).subscribe(
            results => {
                // only redraw removed rows...
               // this.api.upselectRowsdateRowData(
                   // {
                    //    remove: selectRows
                   // }
                );
            }
        ); */
    }
}

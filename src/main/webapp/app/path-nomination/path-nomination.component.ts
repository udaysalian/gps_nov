import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'jhi-path-nomination',
  templateUrl: './path-nomination.component.html',
  styleUrls: [
    'path-nomination.scss'
  ]
})
export class PathNominationComponent implements OnInit {
  columnDefs = [
    {headerName: 'Make', field: 'make'},
    {headerName: 'Model', field: 'model'},
    {headerName: 'Price', field: 'price'}
];
rowData = [];
  message: string;

  constructor() {
    this.message = 'PathNominationComponent message';
  }
  ngOnInit() {
    fetch('https://api.myjson.com/bins/15psn9')
    .then(result=>result.json())
    .then(rowData=>this.rowData=rowData);
    
  }

}

import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../../model/product';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {FormControl, FormGroup, Validators} from '@angular/forms';

export interface UnitPriceElements {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-unit-price',
  templateUrl: './unit-price.component.html',
  styleUrls: ['./unit-price.component.css']
})
export class UnitPriceComponent implements OnInit, AfterViewInit {

  constructor(private productService: ProductService) {
  }

  displayedColumns: string[] = ['unitCount', 'unitPrice'];
  dataSource = new MatTableDataSource<UnitPriceElements>();

  // @ts-ignore
  @ViewChild(MatPaginator) paginator: MatPaginator;

  products: Product[] = [];
  // @ts-ignore
  productForm: FormGroup;

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.findAllProducts();
    this.productForm = new FormGroup({
      product: new FormControl('', [Validators.required]),
    });
  }

  private findAllProducts(): void {
    this.productService.findAllProducts().subscribe(value => {
      this.products = value.content;
    });
  }

  loadUnitPrices(id: any): void {
    this.dataSource.data = [];
    const params = {
      product_id: id
    };
    this.productService.loadUnitPrices(params).subscribe(value => {
      this.dataSource.data = value;
    });
  }

  cartonSize(unitCount: number): any {
    // @ts-ignore
    const unitsPerCarton = this.products.find(p => p.id === +this.productForm.value.product).unitsPerCarton;
    if (unitsPerCarton === undefined) {
      return '';
    }
    if (unitCount % +unitsPerCarton === 0) {
      return 'Carton(s) - ' + (unitCount / +unitsPerCarton);
    }
    return '';
  }
}

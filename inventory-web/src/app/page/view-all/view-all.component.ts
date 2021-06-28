import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../../model/product';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.css']
})
export class ViewAllComponent implements OnInit {

  products: Product[] = [];
  totalPrice: number | undefined = 0.00;
  calForm: FormGroup;

  constructor(private productService: ProductService) {
    this.calForm = new FormGroup({
      type: new FormControl('', [Validators.required]),
      qty: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.findAllProducts();
  }

  private findAllProducts(): void {
    this.productService.findAllProducts().subscribe(value => {
      this.products = value.content;
    });
  }

  calculatePrice(id: any): void {
    if (!this.calForm) {
      return;
    }
    this.totalPrice = 0.00;
    const params = {
      product_id: id,
      units: '0'
    };
    params.units = this.calForm.value.qty;
    if (this.calForm.value.type === 'Carton') {
      // @ts-ignore
      params.units = this.products.find(value => value.id === id).unitsPerCarton * +this.calForm.value.qty;
    }
    this.productService.calculatePrice(params).subscribe(value => {
      this.totalPrice = +value;
    });
  }
}

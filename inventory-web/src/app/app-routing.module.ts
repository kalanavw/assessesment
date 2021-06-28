import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NavigationComponent} from './page/navigation/navigation.component';
import {ViewAllComponent} from './page/view-all/view-all.component';
import {UnitPriceComponent} from './page/unit-price/unit-price.component';

const routes: Routes = [
  {
    path: '', component: NavigationComponent,
    children: [
      {path: '', component: ViewAllComponent},
      {path: 'unit-prices', component: UnitPriceComponent}
    ]
  },
  {path: '**', redirectTo: 'redirect'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

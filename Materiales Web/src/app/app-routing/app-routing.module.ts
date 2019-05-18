import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';

import {MapaDepartamentosComponent} from '../mapa/mapa-departamentos/mapa-departamentos.component';

const routes: Routes = [
    {
        path: 'home',
        component: MapaDepartamentosComponent,
        runGuardsAndResolvers: 'always'
    },
    {
        path: '**',
        redirectTo: 'home',
    }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})
    ],
    exports: [RouterModule],
    declarations: []
})
export class AppRoutingModule {

}

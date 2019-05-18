import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { APP_BASE_HREF } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppModule } from './app.module';
import { AppComponent } from './app.component';

describe('AppComponent', () => {
    let component: AppComponent;
    let fixture: ComponentFixture<AppComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [AppRoutingModule, HttpClientModule, AppModule],
            declarations: [],
            providers: [{ provide: APP_BASE_HREF, useValue: '' }]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AppComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create the app', async(() => {
        expect(component).toBeTruthy();
    }));

    it(`should have as title 'Bookstore'`, async(() => {
        const app = fixture.debugElement.componentInstance;
        expect(app.title).toEqual(component.title);
    }));

    it('should render titles in the navbar', async(() => {
        fixture.detectChanges();
        const compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector('#booksTag').textContent).toContain('Books');
        expect(compiled.querySelector('#authorsTag').textContent).toContain('Authors');
        expect(compiled.querySelector('#editorialTag').textContent).toContain('Editorials');
    }));
});


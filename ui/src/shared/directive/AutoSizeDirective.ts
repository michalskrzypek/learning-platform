import {AfterContentChecked, Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
    selector: 'textarea[autosize]'
})
export class AutoSizeDirective implements AfterContentChecked {

    constructor(public element: ElementRef) {}

    @HostListener('input', ['$event.target'])
    onInput() {
        this.resize();
    }

    ngAfterContentChecked() {
        this.resize();
    }

    resize() {
        const style = this.element.nativeElement.style;
        style.overflow = 'hidden';
        style.height = 'auto';

        const height = this.element.nativeElement.scrollHeight;
        style.height = `${height}px`;
    }
}
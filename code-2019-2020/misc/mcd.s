	.file	"mcd.c"
	.text
	.globl	mcd
	.type	mcd, @function
mcd:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	jmp	.L2
.L5:
	movl	-4(%rbp), %eax
	cmpl	-8(%rbp), %eax
	jle	.L3
	movl	-4(%rbp), %eax
	cltd
	idivl	-8(%rbp)
	movl	%edx, -4(%rbp)
	jmp	.L2
.L3:
	movl	-8(%rbp), %eax
	cltd
	idivl	-4(%rbp)
	movl	%edx, -8(%rbp)
.L2:
	movl	-4(%rbp), %eax
	cmpl	-8(%rbp), %eax
	je	.L4
	cmpl	$0, -4(%rbp)
	je	.L4
	cmpl	$0, -8(%rbp)
	jne	.L5
.L4:
	cmpl	$0, -4(%rbp)
	je	.L6
	movl	-4(%rbp), %eax
	jmp	.L7
.L6:
	cmpl	$0, -8(%rbp)
	je	.L8
	movl	-8(%rbp), %eax
	jmp	.L7
.L8:
	movl	$1, %eax
.L7:
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	mcd, .-mcd
	.ident	"GCC: (Ubuntu 7.4.0-1ubuntu1~18.04.1) 7.4.0"
	.section	.note.GNU-stack,"",@progbits
